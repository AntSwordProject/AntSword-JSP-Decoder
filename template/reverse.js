/**
 * JSP::reverse 解码器
 */

 'use strict';

 module.exports = {
   asoutput: (ext={}) => {
     /**
      * ext = {
      *   opts: 类型为 Object, Shell 配置
      * }
      */
     return `###Reverse###`;
   },
   decode_buff: (buff) => {
     return Buffer.from(buff).reverse();
   }
 }