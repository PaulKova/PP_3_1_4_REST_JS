let users, roles;
adminPage()
userPage()

function userPage() {
    fetch("/api/user")
        .then(response => {
            response.json().then(user => {
                $("#id").text(user.id);
                $("#name").text(user.name);
                $("#surname").text(user.surname);
                $("#age").text(user.age);
                $("#email").text(user.email);
                $("#roles").text(userRole(user));
            })
        })
}


function searchUser(id) {
    return users.find(user => user.id === id);
}

function userRole(user) {
    let userRole = '';
    let tableRoles = user.roles;
    for (let role of tableRoles) {
        userRole += role.role + ' ';
    }
    return userRole;
}

function uModal(id) {
    let user = searchUser(id);
    $("#uID").val(user.id);
    $("#uName").val(user.name);
    $("#uSurname").val(user.surname);
    $("#uAge").val(user.age);
    $("#uEmail").val(user.email);
    $("#uPassword").val(user.password);
    $("#uRoles").empty();
    roles.forEach(role => {
        $("#uRoles").append(
            "<option value=".concat(role.id,
                (user.roles.some(r => r.id === role.id) ? " selected" : ""),
                ">", role.role + "</option>")
        );
    });
}

function dModal(id) {
    let user = searchUser(id);
    $("#dID").val(user.id);
    $("#dName").val(user.name);
    $("#dSurname").val(user.surname);
    $("#dAge").val(user.age);
    $("#dEmail").val(user.username);
    $("#dRoles").empty();
    roles.forEach(role => {
        $("#dRoles").append(
            "<option value=".concat(role.role,
                (user.roles.some(r => r.id === role.id) ? " selected" : ""),
                ">", role.role + "</option>")
        );
    });
}

function addSubmit() {
    let form = $("#newForm");
    $.ajax({
        type: 'POST',
        url: 'api/admin/create',
        data: form.serialize(),
        success: function () {
            form.submit()
        }
    })
}

function updateSubmit() {
    let form = $("#uForm");
    $.ajax('api/edit/' + $("#uID").val(), {
        type: 'PATCH',
        data: form.serialize(),
        success: function () {
            form.submit()
        }
    })
}

function deleteSubmit() {
    let form = $("#dForm");
    $.ajax({
        type: 'DELETE',
        url: "api/delete/" + $("#dID").val(),
        success: function(data) { adminTable(data); },
    })
}

function adminTable() {
    $("#tbodyID").empty();
    users.forEach(user => {
        $("#tbodyID").append("<tr>" +
            "<td>" + user.id + "</td>" +
            "<td>" + user.name + "</td>" +
            "<td>" + user.surname + "</td>" +
            "<td>" + user.age + "</td>" +
            "<td>" + user.username + "</td>" +
            "<td>" + userRole(user) + "</td>" +
            "<td><button class='btn btn-info' data-bs-toggle='modal' data-bs-target='#Um' onclick='uModal(" + user.id + ")' style='color: white'>Edit</button></td>" +
            "<td><button class='btn btn-danger' data-bs-toggle='modal' data-bs-target='#Dm' onclick='dModal(" + user.id + ")' >Delete</button></td>" +
            "</tr>");
    });
}

function adminPage() {
    fetch("/api/roles").then(response => {
        response.json().then(allRoles => {
            roles = allRoles;
        })
    })
    fetch("/api/admin").then(response => {
        response.json().then(allUsers => {
            users = allUsers;
            adminTable()
        })
    })
}


