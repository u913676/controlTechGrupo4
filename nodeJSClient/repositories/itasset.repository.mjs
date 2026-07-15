import {
    PutCommand,
    GetCommand,
    UpdateCommand,
    DeleteCommand,
    QueryCommand,
    ScanCommand
  } from "@aws-sdk/lib-dynamodb";

import { ddb } from "../config/dynamo.mjs";
import * as env from "../config/env.mjs";

const TABLE = env.tableName;
console.log("TABLE", TABLE);
  
  export const addITAsset = (item) =>
    ddb.send(new PutCommand({
      TableName: TABLE,
      Item: item,
    }));
  
  export const getITAssetById = (id) =>
    ddb.send(new GetCommand({
      TableName: TABLE,
      Key: { id },
    }));
  
  export const deleteITAsset = (id) =>
    ddb.send(new DeleteCommand({
      TableName: TABLE,
      Key: { id },
      ConditionExpression: "attribute_exists(id)"
    }));
  
  export const updateITAsset = (id, updateExpression, names, values) =>
    ddb.send(new UpdateCommand({
      TableName: TABLE,
      Key: { id },
      UpdateExpression: updateExpression,
      ExpressionAttributeNames: names,
      ExpressionAttributeValues: values,
      ConditionExpression: "attribute_exists(id)",
      ReturnValues: "ALL_NEW",
    }));
  

  export const listITAssets = (limit, lastKey) =>
    ddb.send(new ScanCommand({
      TableName: TABLE,
      Limit: limit,
      ExclusiveStartKey: lastKey ? JSON.parse(Buffer.from(lastKey, "base64").toString()) : undefined,
    }));