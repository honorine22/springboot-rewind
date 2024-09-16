#!/bin/bash

# Set the API endpoint for CSV export
API_URL="http://localhost:8080/api/users/export"

# Set the destination folder where you want to save the file
DEST_FOLDER="/home/honorine/Documents"

# Ensure the destination folder exists
mkdir -p "$DEST_FOLDER"

# Set the full path to the CSV file
DEST_FILE="$DEST_FOLDER/users.csv"

# Download the CSV file and save it in the specified folder
curl -o "$DEST_FILE" $API_URL

# Confirm the file has been saved
if [ -f "$DEST_FILE" ]; then
  echo "User data has been saved to $DEST_FILE"
else
  echo "Failed to download the user data."
fi
