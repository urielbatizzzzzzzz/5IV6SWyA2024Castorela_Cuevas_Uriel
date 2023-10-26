import os from "os";

const networkInterfaces = os.networkInterfaces();

export default function getLocalIPv4Address() {
  for (const interfaceName in networkInterfaces) {
    const interfaces = networkInterfaces[interfaceName];
    for (const iface of interfaces) {
      if (iface.family === "IPv4" && !iface.internal) {
        return iface.address;
      }
    }
  }
  return "No IPv4 address found";
}

