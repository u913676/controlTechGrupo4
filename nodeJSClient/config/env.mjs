import dotenv from "dotenv";

console.log("process.env.DYNAMO_ENV", process.env.DYNAMO_ENV);
if (process.env.DYNAMO_ENV !== "PROD") {
  dotenv.config();
}

export const isLocal = process.env.DYNAMO_ENV === "local";
export const tableName = process.env.TABLE_NAME;
export const region = process.env.AWS_REGION || "us-east-1";
console.log("process.env.isLocal", isLocal);
console.log("process.env.TABLE_NAME", tableName);