# TO BUILD ANGULAR
FROM node:20-alpine AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod
RUN ls -la /app/dist
# RUN ls -la /app/dist/2024_Olympics_Ticket_Office
RUN find /app/dist -type d | sort

# TO SERVE ANGULAR APP FILES WITH NGINX
FROM nginx:alpine
# RUN mkdir -p /usr/share/nginx/html
COPY --from=build /app/dist/2024_Olympics_Ticket_Office/browser/ /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/conf.d/default.conf
# COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80