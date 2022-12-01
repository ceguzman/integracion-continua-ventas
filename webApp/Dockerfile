# Stage 0, based on Node.js, to build and compile Angular
FROM node:latest as node

#crea carpeta y copia todos los archivos ahí
WORKDIR /app
COPY ./ /app/

RUN npm install --force
RUN npm run build

#Trae el servidor nginx y sirve los archivos estaticos generaros arriba
# Stage 1, based on Nginx, to have only the compiled app, ready for production with Nginx
FROM nginx:alpine
COPY --from=node /app/dist/webApp /usr/share/nginx/html
COPY ./nginx-custom.conf /etc/nginx/conf.d/default.conf
#  (webApp) => Nombre proyetco angular 

#Comando a ejecutar en terminal para levantar la imagen
#docker build . -t webapp:latest
#docker run -d -p 4200:4200 webapp:latest