// Test MongoDB connection
const { MongoClient } = require('mongodb');

const uri = "mongodb+srv://admin:Rohan%40243@cluster1.qq2uyyx.mongodb.net/fruitdb?retryWrites=true&w=majority";

async function testConnection() {
    try {
        const client = new MongoClient(uri);
        console.log('Connecting to MongoDB...');
        await client.connect();
        console.log('✅ MongoDB connection successful!');
        
        const db = client.db('fruitdb');
        const result = await db.admin().ping();
        console.log('✅ Ping successful:', result);
        
        await client.close();
        console.log('✅ Connection closed');
    } catch (error) {
        console.error('❌ MongoDB connection failed:', error.message);
    }
}

testConnection();
