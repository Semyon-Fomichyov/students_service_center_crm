function allRequests() {
    fetch("http://localhost:8080/serviceRequests")
        .then((res) => res.json())
        .then((data) => {
            let output = "";
            data.forEach(function (request) {

                output += `
                <tr>
                <td id="id${request.id}">${request.id}</td>
                <td id="vehicleNumber${request.id}">${request.vehicleNumber}</td> 
                <td id="dateOfCreate${request.id}">${request.dateOfCreate}</td>
                <td id="requestType${request.id}">${request.requestType}</td>
                <td id="problem${request.id}">${request.problem}</td>
                <td id="customer${request.id}">${request.customer}</td>
                <td id="orders${request.id}">${request.orders.map(order => order.order)}
              </tr>

          `;
            });
            document.getElementById("allRequests").innerHTML = output;
        })
}


allRequests()