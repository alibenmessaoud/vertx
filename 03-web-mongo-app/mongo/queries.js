// db.comments.find().skip(db.comments.count() - 1).pretty()
db.comments.find({id: "g7b7e5954f9e"});

/*db.comments.aggregate(
    {"$group" : { "_id": "$id", "count": { "$sum": 1 } } },
    {"$match": {"_id" :{ "$ne" : null } , "count" : {"$gt": 1} } }, 
    {"$sort": {"count" : -1} },
    {"$project": {"id" : "$_id", "_id" : 0} }     
)*/

// db.comments.find({"id": "47e7ag34g8f5a"})

db.comments.find()
