import { z } from "zod";

export const itassetSchema = z.object({
  equipmentType: z.string().min(1),
  brand: z.string().min(1),
  model: z.string().min(1),
  serialNumber: z.string().min(1),
  assetTag: z.string().min(1),
  status: z.string().min(1),
  location: z.string().min(1),
  asignedUser: z.string().min(1),
  purchaseDate: z.string().min(1),
  notes: z.string().min(1)
});