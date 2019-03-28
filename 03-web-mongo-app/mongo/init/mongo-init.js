var comments = [{"id": 1, "content": "Yay my first comment", "user": "user-1"}];

db.createUser(
    {
        user: "admin",
        pwd: "admin",
        roles: [
            {role: "userAdminAnyDatabase", db: "admin"},
            {role: "readWrite", db: "vertxwmdb"}
        ]
    });

db.createUser(
    {
        user: "user",
        pwd: "user",
        roles: [
            {role: "readWrite", db: "vertxwmdb"}
        ]
    });

db.createCollection("comments", {capped: true, size: 52428800, max: 500000});

db.getCollection('comments').insertMany(comments);