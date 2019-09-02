db.createUser({user:'system',pwd:'admin',roles:['readWrite','dbAdmin']});
db.createCollection("picadb");


db.sequence.insert({_id: "hosting",seq: 0}) //generates a sequence for profile
db.descentFormSequence.insert({_id: "hosting",seq: 171812}) //generates a sequence for profile
db.profile.createIndex({"email":1},{unique:true});
db.descentForm.createIndex({"profile.email":1},{unique:true});
db.naturalisation.createIndex({"profile.email":1},{unique:true});


--db.agentSequence.insert({_id:"hosting",seq:1});
--db.agent.createIndex({"id":1},{unique:true});
--
db.createCollection("supervisor");
db.supervisor.createIndex({"agent._id":1},{unique:true, dropDups:true});

db.createCollection("agent");
db.agent.createIndex({"_id":1},{unique:true,dropDups:true});

db.getCollection('agent').insert({_id:1802,name:'Sam James'})
db.getCollection('agent').insert({_id:1803,name:'James Bond'})
db.getCollection('agent').insert({_id:1804,name:'Micheal John'})
db.getCollection('agent').insert({_id:1805,name:'John Bergh'})


db.createCollection("deskClerk");
db.deskClerk.createIndex({"_id":1},{unique:true,dropDups:true});
db.getCollection('deskClerk').insert({_id:9801,name:'Mark Chung'})
db.getCollection('deskClerk').insert({_id:9802,name:'Angela Singh'})
db.getCollection('deskClerk').insert({_id:9803,name:'Helen Clerk'})
db.getCollection('deskClerk').insert({_id:9804,name:'Good Man'})



db.createCollection("supervisor");
---db.getCollection('supervisor').insert({_id:1,name:'Sam James'})
--db.getCollection('supervisor').insert({_id:2,name:'James Bond'})
--db.getCollection('supervisor').insert({_id:3,name:'Micheal John'})
--db.getCollection('supervisor').insert({_id:4,name:'John Bergh'})


db.createCollection('roles');

--application supervisor
db.roles.insert({userId:'applicationsupervisor',password:'admin',
name:'Application Supervisor',url:'/officalForms',desc:'Application supervisor has responsiblity to assing applications to desk clerk'});
--desk clerk
db.roles.insert({userId:'9801',password:'admin',name:'Mark Chung',type:'deskClerk',
url:"/officalForms/deskClerk",,desc:'Desck clerk is resposilbe for reviing internal application assigned by Application supervisor'});db.roles.insert({userId:'9801',password:'admin',name:'Mark Chung',type:'deskClerk',url:'/',desc:'Desck clerk is resposilbe for reviing internal application assigned by Application supervisor'});

db.roles.insert({userId:'9802',password:'admin',name:'Angela Singh',type:'deskClerk',url:"/officalForms/deskClerk",,desc:'Desck clerk is resposilbe for reviing internal application assigned by Application supervisor'});
db.roles.insert({userId:'9803',password:'admin',name:'Helen Clerk',type:'deskClerk',url:"/officalForms/deskClerk",,desc:'Desck clerk is resposilbe for reviing internal application assigned by Application supervisor'});
db.roles.insert({userId:'9804',password:'admin',name:'Good Man',type:'deskClerk',url:"/officalForms/deskClerk",,desc:'Desck clerk is resposilbe for reviing internal application assigned by Application supervisor'});

--//tier one(LocalDeskClerk)
db.roles.insert({userId:'supervisor',password:'admin',url:'/officalForms',desc:'local desk supervisor'});
db.roles.insert({userId:'localdeskclerk',password:'admin',name:'Local Desk Clerk',url:'/officalForms',desc:'Local Desck Clerk is called on applicant, comes for schedule appointment date'});

--//tier two(Compliance Supervisor)
db.roles.insert({userId:'compliancesupervisor',password:'admin',name:'Compliance Supervisor',
url:'/officalForms',desc:'Compliance Supervisor will be called after the local desk clerk has reviewed applicantion'});
//agent login
db.roles.insert({userId:'1802',password:'admin',name:'Sam James',url:"/officalForms/agentView",desc:'agent Sam James, under Supervisor'});
db.roles.insert({userId:'1803',password:'admin',name:'James Bond',url:"/officalForms/agentView",desc:'agent James Bond, under Supervisor'});
db.roles.insert({userId:'1804',password:'admin',name:'Micheal John',url:"/officalForms/agentView",desc:'agent Micheal John, under Supervisor'});
db.roles.insert({userId:'1805',password:'admin',name:'John Bergh',url:"/officalForms/agentView",desc:'agent John Bergh, under Supervisor'});

--//tier three(Operations Manager)
db.roles.insert({userId:'operationsmanager',password:'admin',name:'Operations Manager',url:'/officalForms',desc:'Operations Manager will be called after the Compliance Supervisor has reviewed applicantion'});

--//tier fource(Director)
db.roles.insert({userId:'director',password:'admin',name:'Director',url:'/officalForms',desc:'Director will be called after the Operations Manager has reviewed applicantion'});

db.roles.insert({userId:'ceo',password:'admin',name:'CEO',url:'/officalForms',desc:'CEO committee reviews application after the Director has reviewed applicantion'});

db.roles.insert({userId:'permanentsecretary',password:'admin',name:'Permanent Secretary',url:'/officalForms',desc:'Permanent Secretary committee reviews application after the CEO has reviewed applicantion'});

