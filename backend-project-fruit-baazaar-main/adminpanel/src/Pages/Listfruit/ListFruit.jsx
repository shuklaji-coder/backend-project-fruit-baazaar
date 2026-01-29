import React, { useEffect, useState } from "react";
import axios from "axios";

const ListFruit = () => {
  const [fruits, setFruits] = useState([]);
  const [error, setError] = useState("");
  const [message, setMessage] = useState("");

  // FETCH ALL FRUITS
  const fetchFruits = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/fruit");
      console.log("Fetched fruits:", response.data); // check id field
      setFruits(response.data);
    } catch (err) {
      console.error("Fetch error:", err);
      setError("Failed to load fruits!");
    }
  };

  // DELETE FRUIT
  const deleteFruit = async (id) => {
    const confirmDelete = window.confirm("Are you sure you want to delete?");
    if (!confirmDelete) return;

    try {
      await axios.delete(`http://localhost:8080/api/fruit/${id}`, {
        headers: { "Content-Type": "application/json" }, // if required
      });
      setMessage("Fruit deleted successfully!");
      fetchFruits(); // reload list
    } catch (err) {
      console.error("Delete error:", err.response ? err.response.data : err);
      setError("Failed to delete fruit!");
    }
  };

  useEffect(() => {
    fetchFruits();
  }, []);

  return (
    <div className="container mt-3">
      <h3>Fruit & Vegetable List</h3>

      {message && <div className="alert alert-success">{message}</div>}
      {error && <div className="alert alert-danger">{error}</div>}

      <div className="row">
        {fruits.length > 0 ? (
          fruits.map((fruit) => {
            // Use correct id field (_id or id)
            const id = fruit._id || fruit.id;

            return (
              <div className="col-md-4 mb-3" key={id}>
                <div className="card p-3">
                  <img
                    src={fruit.imageUrl}
                    className="card-img-top"
                    alt={fruit.name}
                    style={{ height: "200px", objectFit: "cover" }}
                    onError={(e) => {
                      e.target.src =
                        "https://via.placeholder.com/200x200?text=No+Image";
                    }}
                  />

                  <h5 className="mt-2">{fruit.name}</h5>
                  <p>{fruit.description}</p>

                  <p>
                    <strong>Category:</strong> {fruit.category}
                  </p>

                  <p>
                    <strong>Price:</strong> ₹{fruit.pricePerKg}/kg
                  </p>

                  <button
                    className="btn btn-danger"
                    onClick={() => deleteFruit(id)}
                  >
                    Delete
                  </button>
                </div>
              </div>
            );
          })
        ) : (
          <p className="text-center mt-3">No fruits available!</p>
        )}
      </div>
    </div>
  );
};

export default ListFruit;
