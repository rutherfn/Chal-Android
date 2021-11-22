
const functions1 = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions1.config().firebase);

const PromisePool = require('es6-promise-pool').default;
const { auth } = require('firebase-admin');
const { database } = require('firebase-functions/v1/firestore');

const MAX_CONCURRENT = 3

var database1 = admin.database()

exports.helloWorld = functions1.https.onRequest((request, response) => {
  const ref = database.
      ref("users/{userId}/email");


  ref.once("value", (snapshot) => {
    console.log( " Here is the snapshot " + snapshot.val());
  }, (errorObject) => {
    console.log("The read failed: " + errorObject.name);
  });
  response.send("Hello from Firebase!");
});

exports.textToLength = functions1.https.onRequest((request, response) => {
  const text = request.query.text;
  const textLength = text.length;
  response.send("whatever message here");
});

exports.checkUserActiveChallenges = functions1
    .pubsub.schedule("every 24 hours").onRun( async context => {
      return null;
    });

exports.newNoteDetected = functions1.database.ref("users/{userId}/username")
    .onWrite((change, context) => {
        const test1 = attemptToGetAllUsers();

        console.log(test1.listUsers)
        
        // working code
        let test = ['EDOGuJDOMOelff5ihv1HgPI1AXv2', 'ZFnOq5F1Ahb0RN666qXdVBkS6Hp2']
        test.forEach((item, index, array) => {
        database1.ref("users/" + item + "/username/").set("NewUserName")
        })
      return null;
    });

    exports.accountcleanup = functions1.pubsub.schedule('every 24 hours').onRun(async context => {
        // Fetch all user details.
        const inactiveUsers = await getInactiveUsers();
        // Use a pool so that we delete maximum `MAX_CONCURRENT` users in parallel.
        const promisePool = new PromisePool(() => deleteInactiveUser(inactiveUsers), MAX_CONCURRENT);
        await promisePool.start();
        functions1.logger.log('User cleanup finished');
      });

      function attemptToGetAllUsers() {
        const records = [
               database1.ref(`users/`).once('value'),
               database1.ref(`users/`).once('value'),
          ];
          
          return Promise.all(records).then(snapshots => {
             const record1 = snapshots[0].val();
             const record2 = snapshots[1].val();
             return null
          });
      }
      
      /**
       * Deletes one inactive user from the list.
       */
      function deleteInactiveUser(inactiveUsers) {
        if (inactiveUsers.length > 0) {
          const userToDelete = inactiveUsers.pop();
          
          // Delete the inactive user.
          return admin.auth().deleteUser(userToDelete.uid).then(() => {
            return functions1.logger.log(
              'Deleted user account',
              userToDelete.uid,
              'because of inactivity'
            );
          }).catch((error) => {
            return functions1.logger.error(
              'Deletion of inactive user account',
              userToDelete.uid,
              'failed:',
              error
            );
          });
        } else {
          return null;
        }
      }
      
      async function getInactiveUsers(users = [], nextPageToken) {
        const result = await admin.auth().listUsers(1000, nextPageToken);
        // Find users that have not signed in in the last 30 days.
        const inactiveUsers = result.users.filter(
            user => Date.parse(user.metadata.lastSignInTime) < (Date.now() - 30 * 24 * 60 * 60 * 1000));
        
        // Concat with list of previously found inactive users if there was more than 1000 users.
        users = users.concat(inactiveUsers);
        
        // If there are more users to fetch we fetch them.
        if (result.pageToken) {
          return getInactiveUsers(users, result.pageToken);
        }
        
        return users;
      }

