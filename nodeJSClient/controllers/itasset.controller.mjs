import * as service from "../services/itasset.service.mjs";
import { itassetSchema } from "../validators/itasset.schema.mjs";
import { ok, created, noContent, badRequest, notFound } from "../utils/response.mjs";


export const addITAssetController = async (event) => {
  const body = JSON.parse(event.body);
  const parsed = itassetSchema.safeParse(body);
  if (!parsed.success) return badRequest(parsed.error);
  const itasset = await service.addService(parsed.data);
  return created(itasset);
};

export const searchITAssetController = async (id) => {
  const itasset = await service.getByIdService(id);
  if (!itasset) return notFound("ITAsset not found");
  return ok(itasset);
};

export const updateITAssetController = async (id, event) => {
  const itasset = await service.getByIdService(id);
  if (!itasset) return notFound("ITAsset not found");
  const body = JSON.parse(event.body);
  const itassetUpdate = await service.updateService(id, body);
  return ok(itassetUpdate);
};

export const deleteITAssetController = async (id) => {
    const itasset = await service.getByIdService(id);
    if (!itasset) return notFound("ITAsset not found");
    await service.deleteService(id);
    return noContent();
};

export const listITAssetController = async (query) => {
  const limit = query?.limit ? parseInt(query.limit) : 10;
  const result = await service.listService(limit, query?.lastKey);
  return ok(result);
};