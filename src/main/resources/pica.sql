db.createUser({user:'system',pwd:'admin',roles:['readWrite','dbAdmin']});
db.createCollection("picadb");


db.sequence.insert({_id: "hosting",seq: 0}) //generates a sequence for profile
db.descentFormSequence.insert({_id: "hosting",seq: 171812}) //generates a sequence for profile
db.profile.createIndex({"email":1},{unique:true});
db.descentForm.createIndex({"profile.email":1},{unique:true});

