import { DynamoDBClient } from "@aws-sdk/client-dynamodb";
import { DynamoDBDocumentClient } from "@aws-sdk/lib-dynamodb";
import * as env from "../config/env.mjs";

let config = { region: env.region }
if (env.isLocal === true) {
  config.endpoint = "http://localhost:8000";
}

export const ddb = new DynamoDBClient(config);
export const docClient = DynamoDBDocumentClient.from(ddb);