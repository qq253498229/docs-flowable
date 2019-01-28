#!/bin/bash

asciidoctor -a stylesheet=../base/flowable.css -o ../index.html index-html.adoc
asciidoctor -a stylesheet=../base/flowable.css -o ../migration.html migration.adoc

rm -rf ../images
mkdir ../images
cp -r images ..

## Copy Base Images
cp -r ../base/images ../