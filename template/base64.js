/**
 * JSP::base64 解码器
 */

 'use strict';

 module.exports = {
   asoutput: (ext={}) => {
     /**
      * ext = {
      *   opts: 类型为 Object, Shell 配置
      * }
      */
     return `###Base64###`;
   },
   decode_buff: (buff) => {
    return Buffer.from(buff.toString(), 'base64');
   }
 }