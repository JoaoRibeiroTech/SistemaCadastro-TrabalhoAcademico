#!/bin/bash
docker exec -t sistema-postgres pg_dump -U postgres sistemacadastro > backup/sistemacadastro.sql