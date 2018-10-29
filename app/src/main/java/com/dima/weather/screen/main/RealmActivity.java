package com.dima.weather.screen.main;

import com.dima.weather.screen.base.BaseActivity;

/**
 * Created by Liashenko Dima on 28.02.2018.
 */

public class RealmActivity extends BaseActivity {
    // Define your model class by extending RealmObject
//    public class Dog extends RealmObject {
//        private String name;
//        private int age;
//
//        // ... Generated getters and setters ...
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//    }
//
//    public class Person extends RealmObject {
//        @PrimaryKey
//        private long id;
//        private String name;
//        private RealmList<Dog> dogs; // Declare one-to-many relationships
//
//        // ... Generated getters and setters ...
//
//        public long getId() {
//            return id;
//        }
//
//        public void setId(long id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public RealmList<Dog> getDogs() {
//            return dogs;
//        }
//
//        public void setDogs(RealmList<Dog> dogs) {
//            this.dogs = dogs;
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        // Use them like regular java objects
//        Dog dog = new Dog();
//        dog.setName("Rex");
//        dog.setAge(1);
//
//// Initialize Realm (just once per application)
//        Realm.init(this);
//
//        // Get a Realm instance for this thread
//        Realm realm = Realm.getDefaultInstance();
//
//        // Query Realm for all dogs younger than 2 years old
//        final RealmResults<Dog> puppies = realm.where(Dog.class).lessThan("age", 2).findAll();
//        puppies.size(); // => 0 because no dogs have been added to the Realm yet
//
//// Persist your data in a transaction
//        realm.beginTransaction();
//        final Dog managedDog = realm.copyToRealm(dog); // Persist unmanaged objects
//        Person person = realm.createObject(Person.class); // Create managed objects directly
//        person.getDogs().add(managedDog);
//        realm.commitTransaction();
//
//// Listeners will be notified when data changes
//        puppies.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Dog>>()
//
//        {
//            @Override
//            public void onChange(RealmResults<Dog> results, OrderedCollectionChangeSet changeSet) {
//                // Query results are updated in real time with fine grained notifications.
//                changeSet.getInsertions(); // => [0] is added.
//            }
//        });
//
//// Asynchronously update objects on a background thread
//        realm.executeTransactionAsync(new Realm.Transaction()
//
//        {
//            @Override
//            public void execute(Realm bgRealm) {
//                Dog dog = bgRealm.where(Dog.class).equalTo("age", 1).findFirst();
//                dog.setAge(3);
//            }
//        }, new Realm.Transaction.OnSuccess()
//
//        {
//            @Override
//            public void onSuccess() {
//                // Original queries and Realm objects are automatically updated.
//                puppies.size(); // => 0 because there are no more puppies younger than 2 years old
//                managedDog.getAge();   // => 3 the dogs age is updated
//            }
//        });
//
//
//    }

}
