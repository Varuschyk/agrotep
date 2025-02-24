# Agrotep tachtask

## Multiple fish images 

---

Moved fish creation business logic into separated `FishService` and improve implementation. Sure, [index.html](src/main/resources/templates/index.html)
edited too.


## Authentication

---

Project has inmemory accounts.
```
User: user:user

Admin: admin:admin
```

## Database update

---
For change 3rd requirement I used the Liquibase migration mechanism that allowed me without problems
update existing table `fish` by adding new column `count` with saving existing data.

File [changelog-v1.yml](src/main/resources/db/changelog/version/changelog-v1.yml) creates table and inserts
initial data.
Next changelog - [changelog-v2.yml](src/main/resources/db/changelog/version/changelog-v2.yml) updates `fish` table
and adds column `count`.

After that change we can add new column in [Fish.java](src/main/java/technikal/task/fishmarket/persistence/entity/Fish.java) entity
for consistency schema and our java object. Also, we need to implement new logic with that new field. For that I simply
initialize that field by creation new `fish`. But, for best practice we should implement `FishServiceV2Impl`. And improve
UI representation in [index.html](src/main/resources/templates/index.html).


    