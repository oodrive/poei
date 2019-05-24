FROM node:11-alpine AS builder

WORKDIR /opt/src
COPY . .

RUN rm -rf dist
RUN npm install
RUN npm run build --prod
# ---------------
FROM nginx:1.16-alpine

COPY --from=builder /opt/src/dist/poei-front /usr/share/nginx/html
