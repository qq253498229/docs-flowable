FROM asciidoctor/docker-asciidoctor:latest as builder

COPY docs /documents

RUN cd bpmn && \
asciidoctor -a stylesheet=../base/flowable.css -o ../index.html index-html.adoc && \
asciidoctor -a stylesheet=../base/flowable.css -o ../migration.html migration.adoc && \
rm -rf ../images && \
mkdir ../images && \
cp -r images .. && \
cp -r ../base/images ../

FROM nginx:1.15-alpine

RUN rm -rf /usr/share/nginx/html/*

COPY --from=builder /documents /usr/share/nginx/html

CMD [ "nginx", "-g", "daemon off;"]

