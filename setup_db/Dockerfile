FROM mysql
WORKDIR /app
COPY . .
RUN chmod +x wait-for-it.sh
RUN chmod +x populate.sh
CMD ["./wait-for-it.sh", "-t", "120", "db:3306", "--" , "./populate.sh"]