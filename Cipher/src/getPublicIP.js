import os from "os";

export const localIPv4 = () => {
  const networkInterfaces = os.networkInterfaces();
  for (const key in networkInterfaces) {
    if (networkInterfaces.hasOwnProperty(key)) {
      const interfaces = networkInterfaces[key];
      for (const iface of interfaces) {
        if (iface.family === "IPv4") {
          return iface.address;
        }
      }
    }
  }
  return null;
};

export const IP = "192.168.1.67";
