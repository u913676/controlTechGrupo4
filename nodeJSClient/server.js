import express from "express";
import dotenv from "dotenv";
import itassetssRouter from "./routers/itasset.routers.mjs";

const app = express();
app.use(express.json());
app.use("/", itassetssRouter);
app.listen(3000, () => console.log("Servidor local en http://localhost:3000"));