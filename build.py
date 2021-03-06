#!/usr/bin/env python
# coding:UTF-8

import os
import sys
import base64
import time
import shutil
import subprocess
import platform

# javac路径 如果 javac 不在 PATH 中, 请自己添加
javapath = r'javac'

pathsep = os.pathsep
distDir = "./dist/"

if os.path.exists(distDir):
    shutil.rmtree(distDir)
shutil.copytree("./template/", distDir)

for root, dirs, files in os.walk('./src'):
    for f in files:
        if f.endswith(".java"):
            path = os.path.join(root, f)
            print('------------------------------------------------------------')
            print(path)
            cmd = '"{javapath}" {path}'.format(
                javapath=javapath,
                path=path
            )
            print(cmd)
            p=subprocess.Popen(
              cmd,
              stdout=subprocess.PIPE,
              stderr=subprocess.PIPE,
              shell=True
            )
            out, err = p.communicate()
            try:
                print(err.decode())
            except:
                print(str(err))
            targetclass = path.replace('.java', '.class')
            if os.path.exists(targetclass):
                with open(targetclass, 'rb') as f:
                    content = f.read()
                res = str(base64.b64encode(content))
                if len(path.split(os.path.sep)) == 3:
                    name = path.split(os.path.sep)[2].split('.')[0].replace('Asoutput', '')
                    dispath = os.path.join(
                        distDir,
                        name.lower()+".js"
                    )
                    with open(dispath) as disp:
                        result = disp.read().replace('###'+name+'###', res)
                    with open(dispath, mode="w") as disp:
                        disp.write(result)
                else:
                    exit("error")
