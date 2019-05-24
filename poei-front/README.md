# Front for POEI

> Contains components that listen to servers

# Prerequisites

- docker
- docker-compose
- your IP
- the IPs of the groups

# Getting started

Edit the following files:

- `src/environments/environment.prod.ts`
  - in the `host` value, set it to `http://<your_ip>`
- `nginx.conf`
  - change the values of the proxy passes for the locations:
    - `/sample`
    - `/file-storage`
    - `/password-manager`
    - `/secured-box`

To launch the front:

```bash
docker-compose up -d
firefox http://<your_ip>
```
