import express from "express";
import { handler } from "../index.mjs";

const router = express.Router();

router.all("/itassets", async (req, res) => {
  const event = {
    httpMethod: req.method,
    pathParameters: {},
    body: JSON.stringify(req.body),
    queryStringParameters: req.query
  };
  const result = await handler(event);
  res.status(result.statusCode).send(result.body);
});

router.all("/itassets/:id", async (req, res) => {
  const event = {
    httpMethod: req.method,
    pathParameters: { id: req.params.id },
    body: JSON.stringify(req.body),
    queryStringParameters: req.query
  };
  const result = await handler(event);
  res.status(result.statusCode).send(result.body);
});

export default router;