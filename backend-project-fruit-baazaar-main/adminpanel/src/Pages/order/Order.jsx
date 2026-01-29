import React, { useEffect, useState } from "react";
import axios from "axios";

const Order = () => {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchOrders();
  }, []);

  const fetchOrders = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/orders/all"
      );
      setOrders(response.data);
    } catch (err) {
      console.error("Error fetching orders:", err);
      setError("Failed to load orders");
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <h4>Loading orders...</h4>;
  if (error) return <h4 style={{ color: "red" }}>{error}</h4>;

  return (
    <div className="container-fluid">
      <h3 className="mb-4">📦 Orders</h3>

      {orders.length === 0 ? (
        <p>No orders found</p>
      ) : (
        <div className="table-responsive">
          <table className="table table-bordered table-hover align-middle">
            <thead className="table-dark">
              <tr>
                <th>#</th>
                <th>User Email</th>
                <th>Total Amount</th>
                <th>Payment Status</th>
                <th>Payment ID</th>
                <th>Order Date</th>
                <th>Billing Address</th>
              </tr>
            </thead>

            <tbody>
              {orders.map((order, index) => (
                <tr key={order.id}>
                  <td>{index + 1}</td>

                  <td>{order.userEmail || "N/A"}</td>

                  <td>₹{order.totalAmount}</td>

                  <td>
                    <span
                      className={
                        order.paymentStatus === "PAID"
                          ? "badge bg-success"
                          : "badge bg-warning text-dark"
                      }
                    >
                      {order.paymentStatus || "PENDING"}
                    </span>
                  </td>

                  <td>{order.paymentId || "COD"}</td>

                  <td>
                    {order.createdAt
                      ? new Date(order.createdAt).toLocaleString()
                      : "N/A"}
                  </td>

                  <td>
                    {order.billingAddress ? (
                      <>
                        <div><b>{order.billingAddress.fullName}</b></div>
                        <div>{order.billingAddress.mobile}</div>
                        <div style={{ fontSize: "13px" }}>
                          {order.billingAddress.street},{" "}
                          {order.billingAddress.city}
                        </div>
                        <div style={{ fontSize: "13px" }}>
                          {order.billingAddress.state} -{" "}
                          {order.billingAddress.pincode}
                        </div>
                      </>
                    ) : (
                      "N/A"
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default Order;
