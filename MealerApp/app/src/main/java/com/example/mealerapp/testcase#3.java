
       @Test
        public void registeringAndDeletingClient_isCorrect() {

            UserDatabase dtb = new UserDatabase();
            User client = new Client("Joan", "Laith", "Joanlaith@gmail.com", "joan123", "Client", "35 Cloverfield lane", "678383732837");
            dtb.registerUser(client);
            String userID = FirebaseAuth.getInstance().getID();
            dtb.DeleteClient(userId, "2022/11/18");

            Database.retrieveListener clientListener = new Database.retreiveListener() {
                @Override
                public void onDataReceived(Object data) {
                    Boolean isDeleted = Boolean.valueOf(data.toString());
                    assertTrue(isSuspended);
                }

                @Override
                public void onError() {
                }
            };
            dtb.getInformation(FirebaseDatabase.getInstance().getReference("USERS").child(userID).child("isDeleted"), cookListener);
        }

        @After
        public void deleteTestClient() {
            UsersDataBase dtb = new UsersDataBase();
            dtb.deleteUser(client);
        }
    