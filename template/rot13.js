/**
 * jsp::rot13 解码器
 */

 'use strict';
 const rot13encode = (s) => {
   //use a Regular Expression to Replace only the characters that are a-z or A-Z
   return s.replace(/[a-zA-Z]/g, function (c) {
     // Get the character code of the current character and add 13 to it If it is
     // larger than z's character code then subtract 26 to support wrap around.
     return String.fromCharCode((c <= "Z" ?
         90 :
         122) >= (c = c.charCodeAt(0) + 13) ?
       c :
       c - 26);
   });
 };
 
 module.exports = {
   asoutput: () => {
     return `###Rot13###`;
   },
   decode_buff: (buff) => {
     return Buffer.from(rot13encode(buff.toString()));
   }
 }