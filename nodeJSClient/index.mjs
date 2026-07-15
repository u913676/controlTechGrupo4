import * as controller from "./controllers/itasset.controller.mjs";
import * as env from "./config/env.mjs";

export const handler = async (event) => {
  try {

    let id;
    let method;

    if (env.isLocal === true) {
      id = event.pathParameters?.id;
      method = event.httpMethod;
    } else {
      id = event.pathParameters?.id;
      method = event.requestContext.http.method;
    }

    console.log("id", id);
    console.log("method", method);

    if (method === "POST") return controller.addITAssetController(event);
    if (method === "GET" && !id) return controller.listITAssetController(event.queryStringParameters);
    if (method === "GET" && id) return controller.searchITAssetController(id);
    if (method === "PUT" && id) return controller.updateITAssetController(id, event);
    if (method === "DELETE" && id) return controller.deleteITAssetController(id);

    return {
      statusCode: 405,
      body: JSON.stringify({ message: "Method Not Allowed" })
    };

  } catch (error) {
    console.error("Unexpected Error:", error);
    return {
      statusCode: 500,
      body: JSON.stringify({ message: "Internal Server Error" })
    }; 
  }
};