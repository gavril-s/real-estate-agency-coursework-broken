# Use the official PostgreSQL image as a base image
FROM postgres:latest

# Set environment variables for PostgreSQL
ENV POSTGRES_DB=real_estate_db
ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=admin

# Expose the default PostgreSQL port
EXPOSE 5432
