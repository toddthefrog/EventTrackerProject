window.addEventListener('load', function(e) {
    console.log('document loaded');
    init();
});

function init() {
    document.userForm.lookup.addEventListener('click', function(event) {
        event.preventDefault();
        let userId = document.userForm.userId.value;
        if (!isNaN(userId) && userId > 0) {
            getUser(userId);
        }
    });
}

function getUser(userId) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `api/users/${userId}`);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // /TODO
                console.log('found user ' + userId);
                let user = JSON.parse(xhr.responseText);
                displayUser(user);
            } else {
                document.getElementById('userData').textContent = 'User not found.';
            }
        }
    };
    xhr.send(null);
}

function addUser() {
    console.log('addUser() called.');
    let f = document.addUserForm;
    let user = {};
    let schedule = [];
    user.firstName = f.firstName.value;
    user.lastName = f.lastName.value;
    user.isActive = f.isActive.value;
    user.schedule = schedule;

    let xhr = new XMLHttpRequest();
    xhr.open('POST', `api/users`);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 201) {
                let addedUser = JSON.parse(xhr.responseText);
                console.log(addedUser);
                displayUser(addedUser);
            }
        }
    }
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(JSON.stringify(user));
}

function deleteSchedule(scheduleId) {
    let xhr = new XMLHttpRequest();
    xhr.open('DELETE', `api/users/${userId}/schedules/${scheduleId}`);
    console.log('in deleteSchedule');
//    xhr.onreadystatechange = function() {
//        if (xhr.readyState === 4) {
//        	if (xhr.status === 201) {
//            }
//            console.log('schedule deleted')
//        }
//    };
    xhr.send(null);
}

function displayUser(user) {
    var dataDiv = document.getElementById('userData');
    dataDiv.textContent = '';
    let h1 = document.createElement("h1");
    h1.textContent = user.id;
    dataDiv.appendChild(h1);
    let ul = document.createElement("ul");
    let li = document.createElement("li");
    li.textContent = user.firstName;
    ul.appendChild(li);
    li = document.createElement("li");
    li.textContent = user.lastName;
    ul.appendChild(li);
    dataDiv.appendChild(ul);
    var elevatedId = user.id;
    getAndDisplayUserSchedules(user.id);
}

function getAndDisplayUserSchedules(userId) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `api/users/${userId}/schedules`);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log('found schedules for user: ' + userId);
                let schedules = JSON.parse(xhr.responseText);
                displayUserSchedules(schedules);
            }
        }
    };
    xhr.send(null);
}

function displayUserSchedules(schedules) {
    var scheduleDiv = document.getElementById('scheduleData');
    scheduleDiv.className = 'container';
    scheduleDiv.textContent = '';
    for (var i = 0; i < schedules.length; i++) {
    	let pillSizeInUg = schedules[i].pillSizeInUg / 1000;
    	let doseSizeInUg = schedules[i].doseInUg / 1000;
		let div = 
			'<div class="card" style="width: 50rem;">' +
				'<form name="scheduleUpdateForm">' +
				    '<div class="card-header">' +
				    	'Schedule ' + schedules[i].id +
				    '</div>' +
				    '<div class="card-body">' +
				    	'<ul class="list-group">' +
						    '<li class="list-group-item">Prescription Name: ' + schedules[i].prescriptionName + '</li>' +
						    '<br>' +
						    '<div class="collapse" id="collapseExample' + [i] + '">' +
						    	'<input type="text" name="prescriptionName" value="' + schedules[i].prescriptionName + '">' +
						    '</div>' +
						    '<br>' +
						    '<li class="list-group-item">Pill Size: ' + pillSizeInUg + ' milligrams.' + '</li>' +
						    '<br>' +
						    '<div class="collapse" id="collapseExample' + [i] + '">' +
						    	'<input type="text" name="pillSizeInUg" value="' + pillSizeInUg + '">' +
						    '</div>' +
						    '<br>' +
						    '<li class="list-group-item">Dose: ' + doseSizeInUg + ' milligrams.' + '</li>' +
						    '<br>' +
						    '<div class="collapse" id="collapseExample' + [i] + '">' +
						    	'<input type="text" name="doseSizeInUg" value="' + doseSizeInUg + '">' +
						    '</div>' +
						    '<br>' +
						    '<li class="list-group-item">' + schedules[i].timesDaily + ' time(s) daily' + '</li>' +
						    '<br>' +
						    '<div class="collapse" id="collapseExample' + [i] + '">' +
						    	'<input type="text" name="timesDaily" value="' + schedules[i].timesDaily + '">' +
						    '</div>' +
						    '<br>' +
						    '<div class="collapse" id="collapseExample' + [i] + '">' +
						    	'<input name="scheduleId" type="hidden" value="' + schedules[i].id + '">' +
						    	'<button class="btn btn-primary" type="update" id="update" name="update">Update</button> ' +
						    	'<button class="btn btn-primary" type="remove" id="remove" name="remove">Remove</button>' +
						    '</div>' +
						    '<br>' +
						'</ul>' +
					    '<p>' +
					    	'<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample' + [i] + '" role="button" aria-expanded="false" aria-controls="collapseExample' + [i] + '">Display Admin Controls</a>' +
					    '</p>' +
				    '</div>' +
				'</form>' +
		    '</div>';
		$(scheduleDiv).append(div);
	}
    $(document).on('click', 'remove', function(event) {
		event.preventDefault();
		let scheduleId = document.scheduleUpdateForm.userId.value;
		console.log("before delete");
        deleteSchedule(scheduleId);
	});
}


function addUser() {
    console.log('addUser() called.');
    let f = document.addUserForm;
    let user = {};
    let schedule = [];
    user.firstName = f.firstName.value;
    user.lastName = f.lastName.value;
    user.isActive = f.isActive.value;
    user.schedule = schedule;

    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'api/users');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 201) {
                let addedUser = JSON.parse(xhr.responseText);
                console.log(addedUser);
                displayUser(addedUser);
            }
        }
    }
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(JSON.stringify(user));
}
