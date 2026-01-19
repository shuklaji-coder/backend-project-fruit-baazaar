import React, { useEffect, useState } from "react";
import axios from "axios";

const AddFruit = () => {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [pricePerKg, setPricePerKg] = useState("");
  const [image, setImage] = useState(null);
  const [fruits, setFruits] = useState([]);

  // 🔹 Load fruits
  const loadFruits = async () => {
    const res = await axios.get("http://localhost:8080/api/fruit");
    setFruits(res.data);
  };

  useEffect(() => {
    loadFruits();
  }, []);

  // 🔹 Add fruit
  const handleSubmit = async (e) => {
    e.preventDefault();

    const fruit = {
      name,
      description,
      category: "fruit",
      pricePerKg: Number(pricePerKg),
    };

    const formData = new FormData();
    formData.append(
      "fruit",
      new Blob([JSON.stringify(fruit)], { type: "application/json" })
    );
    formData.append("file", image);

    await axios.post("http://localhost:8080/api/fruit", formData);

    setName("");
    setDescription("");
    setPricePerKg("");
    setImage(null);

    loadFruits();
  };

  // 🔹 Delete fruit
  const deleteFruit = async (id) => {
    await axios.delete(`http://localhost:8080/api/fruit/${id}`);
    loadFruits();
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.heading}>Add Fruit</h2>

      {/* FORM */}
      <form style={styles.form} onSubmit={handleSubmit}>
        <input
          style={styles.input}
          placeholder="Fruit Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
        <input
          style={styles.input}
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        />
        <input
          style={styles.input}
          type="number"
          placeholder="Price / Kg"
          value={pricePerKg}
          onChange={(e) => setPricePerKg(e.target.value)}
          required
        />
        <input
          style={styles.input}
          type="file"
          onChange={(e) => setImage(e.target.files[0])}
          required
        />
        <button style={styles.addBtn}>Add Fruit</button>
      </form>

      {/* TABLE */}
      <table style={styles.table}>
        <thead>
          <tr>
            <th style={styles.th}>Name</th>
            <th style={styles.th}>Description</th>
            <th style={styles.th}>Price</th>
            <th style={styles.th}>Action</th>
          </tr>
        </thead>
        <tbody>
          {fruits.map((f) => (
            <tr key={f.id}>
              <td style={styles.td}>{f.name}</td>
              <td style={styles.td}>{f.description}</td>
              <td style={styles.td}>₹ {f.pricePerKg}</td>
              <td style={styles.td}>
                <button
                  style={styles.deleteBtn}
                  onClick={() => deleteFruit(f.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

// 🔹 Inline CSS (same file)
const styles = {
  container: {
    maxWidth: "900px",
    margin: "30px auto",
    fontFamily: "Arial, sans-serif",
  },
  heading: {
    marginBottom: "20px",
  },
  form: {
    display: "grid",
    gridTemplateColumns: "repeat(4, 1fr)",
    gap: "12px",
    marginBottom: "30px",
  },
  input: {
    padding: "10px",
    fontSize: "14px",
  },
  addBtn: {
    gridColumn: "span 4",
    padding: "12px",
    background: "#0d6efd",
    color: "#fff",
    border: "none",
    cursor: "pointer",
    fontSize: "15px",
  },
  table: {
    width: "100%",
    borderCollapse: "collapse",
  },
  th: {
    background: "#f2f2f2",
    padding: "12px",
    borderBottom: "1px solid #ccc",
    textAlign: "left",
  },
  td: {
    padding: "12px",
    borderBottom: "1px solid #ddd",
  },
  deleteBtn: {
    background: "#dc3545",
    color: "#fff",
    border: "none",
    padding: "6px 10px",
    cursor: "pointer",
  },
};

export default AddFruit;
