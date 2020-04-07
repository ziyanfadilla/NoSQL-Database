/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produk;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.result.UpdateResult;
import java.util.Date;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author ziyan
 */
public class TestDB {
    public static void main(String[] args) {
        try{
            //koneksi ke database MongoDB
            MongoDatabase database = Koneksi.sambungDB();
            
            //melihat daftar koleksi (tabel)
            System.out.println("===============================");
            System.out.println("daftar tabel dalam database");
            MongoIterable<String> tables = database.listCollectionNames();
            for (String name : tables){
                System.out.println(name);
            }
            
            //menambah data
            System.out.println("===============================");
            System.out.println("menambah data ");
            MongoCollection<Document> col = database.getCollection("produk");
                Document doc = new Document();
                doc.put("nama", "Printer InkJet");
                doc.put("harga", 1204000);
                doc.put("tanggal", new Date());
                col.insertOne(doc);
                    System.out.println("data tersimpan dikoleksi :D ");
                   
            //mendapatkan_id dari dokumen yang baru diInsert
            Object id = new ObjectId(doc.get("_id").toString());
                    
            //menampilkan data
                System.out.println("=========================");
                System.out.println("data dalam koleksi produk");
                MongoCursor<Document> cursor = col.find().iterator();
                    while (cursor.hasNext()) {
                        System.out.println(cursor.next().toJson());
                     }
                    
            //menghapus data
                    col.deleteOne(eq("_id",id));
                    
            //menampilkan data 
                System.out.println("=========================");
                System.out.println("data dalam koleksi produk");
                cursor = col.find().iterator();
                    while (cursor.hasNext()) {
                        System.out.println(cursor.next().toJson());
                     }                   
        } catch(Exception e){
            
        }
    }
    
}
