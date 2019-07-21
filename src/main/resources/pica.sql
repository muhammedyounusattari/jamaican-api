db.createUser({user:'system',pwd:'admin',roles:['readWrite','dbAdmin']});
db.createCollection("picadb");


db.sequence.insert({_id: "hosting",seq: 0}) //generates a sequence for profile
db.descentFormSequence.insert({_id: "hosting",seq: 171812}) //generates a sequence for profile
db.profile.createIndex({"email":1},{unique:true});
db.descentForm.createIndex({"profile.email":1},{unique:true});

--db.agentSequence.insert({_id:"hosting",seq:1});
--db.agent.createIndex({"id":1},{unique:true});
--
db.createCollection("supervisor");
db.supervisor.createIndex({"agent._id":1},{unique:true, dropDups:true});

db.createCollection("agent");
db.agent.createIndex({"_id":1},{unique:true,dropDups:true});

db.getCollection('agent').insert({_id:1,name:'Sam James'})
db.getCollection('agent').insert({_id:2,name:'James Bond'})
db.getCollection('agent').insert({_id:3,name:'Micheal John'})
db.getCollection('agent').insert({_id:4,name:'John Bergh'})


db.createCollection("supervisor");
db.getCollection('supervisor').insert({_id:1,name:'Sam James'})
db.getCollection('supervisor').insert({_id:2,name:'James Bond'})
db.getCollection('supervisor').insert({_id:3,name:'Micheal John'})
db.getCollection('supervisor').insert({_id:4,name:'John Bergh'})
