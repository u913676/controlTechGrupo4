import { randomUUID } from "crypto";
import * as repo from "../repositories/itasset.repository.mjs";


export const addService = async (data) => {
  const newITAsset = { ...data, id: randomUUID() };
  await repo.addITAsset(newITAsset);
  return newITAsset;
};

export const getByIdService = async (id) => {
  const result = await repo.getITAssetById(id);
  return result.Item;
};

export const deleteService = async (id) => {
  await repo.deleteITAsset(id);
};

export const updateService = async (id, body) => {

  let updateExpression = "set ";
  let names = {};
  let values = {};

  const keys = Object.keys(body);

  keys.forEach((key, index) => {
    updateExpression += `#${key} = :${key}`;
    if (index < keys.length - 1) updateExpression += ", ";
    names[`#${key}`] = key;
    values[`:${key}`] = body[key];
  });

  const result = await repo.updateITAsset(id, updateExpression, names, values);
  return result.Attributes;
};

export const listService = async (limit = 10, lastKey) => {
  const result = await repo.listITAssets(limit, lastKey);

  return {
    items: result.Items,
    nextKey: result.LastEvaluatedKey
      ? Buffer.from(JSON.stringify(result.LastEvaluatedKey)).toString("base64")
      : null,
  };
};