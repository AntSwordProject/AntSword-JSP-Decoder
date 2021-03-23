# AntSword-JSP-Decoder 解码器模版

## 编译

```
python build.py
```

## 使用

将 `dist/` 目录生成的 js 覆盖 `antSword/source/core/jsp/decoder/` 目录下的文件

也可以直接从 https://github.com/AntSwordProject/AntSword-JSP-Decoder/actions/ 的 Artifacts 中下载自动编译好的版本


## 解码器 Dev

以 Reverse 解码器为例说明：

> 该解码器的功能是将返回包的数据反转

编写 AsoutputReverse.java 内容如下:

```java
public class AsoutputReverse {
  String res;

  public AsoutputReverse(String str) {
    // 这里编写对 str 处理的逻辑, 最后将值传给 res
    res = new StringBuffer(str).reverse().toString();
  }

  // 请保持 toString 方法, 不要修改内容
  @Override
  public String toString() {
    return res;
  }
}
```

编译，并获取 .class 文件 base64 后的内容, 注意只有一行

```bash
$ javac AsoutputReverse.java
$ base64 -w 0 AsoutputReverse.class
```

打开 AntSword 进入编码设置，创建「解码器」，内容如下:

```
/**
 * JSP::reverse 解码器
 */

 'use strict';

 module.exports = {
   asoutput: () => {
     // 这里是你的 .class 文件的 base64 后的内容
     return `yv66vgAAADMAHgoACAATBwAUCgACABUKAAIAFgoAAgAXCQAHABgHA...`;
   },
   decode_buff: (data) => {
     // 这里是解返回包的逻辑
     return Buffer.from(data).reverse();
   }
 }
```

注:

* 如果需要使用 build.py 自动编译，需要将 .java 文件放在 src/ 目录下，并以 `Asoutput` 作为文件名前缀
* 编译字节码时使用的 jdk 版本影响着编码器的普适性，建议在 jdk5/jdk6 等低版本进行编译


## 测试

使用 `AsTest.java` 用来测试你写好的解码器类是否如预期

```
$ javac AsTest.java
$ java AsTest src/AsoutputReverse.class

[+] Plain Text:
This is AntSword JSP Decoder Test Message.

[+] Asoutput:
.egasseM tseT redoceD PSJ drowStnA si sihT
```
