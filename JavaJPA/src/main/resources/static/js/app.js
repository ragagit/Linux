/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validate() {
	var name = document.getElementById("id").value;
	if (name == '') {
		alert('Please enter a valid id.');
		return false;
	} else {
		return true;
	}
}

