function checkReqFields(form) {
    for(i=0;i<form.length;i++) {
        if( form.elements[i].name.indexOf ("req:") != -1){
           if(form.elements[i].value==''){
                form.elements[i].color='red';
                form.elements[i].focus();
                alert('Please, fill required feids!\n (marked by asterisks)');
                return false;
          }
        }
    }
    return true;
}

function checkReqFields2(form) {
    for(i=0;i<form.length;i++) {
        if( form.elements[i].name.indexOf ("req:") != -1){
           if(form.elements[i].value==''){
                return false;
          }
        }
    }
    return true;
}

function  validateRow(form,url){
    if(checkReqFields(form)){
        form.action=url;
        form.submit();
    }
}

function deleteRow(form){
    if(checkSelected(form)){
        if(confirm("Выбранные записи будут удалены!\nВы уверены?")){
            form.action='?act=del';
            form.submit();
        }
    }
}

/*
function deleteRow(form,action){
    if(checkSelected(form)){
        if(confirm("Выбранные записи будут удалены!\nВы уверены?")){
            form.action='?act='+action;
            form.submit();
        }
    }
}
*/

function checkSelected(form){
    for (i = 0; i < form.length; i++){
        if(form.elements[i].checked == true){
            return true;
        }
    }
    alert('Не выбрано ни одной записи!');
    return false;
}

var selected = false;

function toggleSelect(el){
    var form = el.form;
    for (i = 0; i < form.length; i++){
      var el = form.elements[i];
      if(el.name=='id'){
        el.checked = !selected;
        /*toggleHighlight(theel);*/
      }
    }
    el.checked = !selected;
    selected = !selected;
}

function toggleHighlight(el) {
    var r = null;
    if (el.parentNode && el.parentNode.parentNode) {
        r = el.parentNode.parentNode;
    }
    else if (el.parentElement && el.parentElement.parentElement) {
        r = el.parentElement.parentElement;
    }
    if (r) {
        if (el.checked) {
            r.className = "jive-pm-msg-row-sel";
        }
        else {
            r.className = "jive-pm-msg-row";
        }
    }
}


/*
function setButtonStatus(form){
    if(checkSelected(form)>0){
        form.DeleteButton.disabled='false';
    }
    else{
        form.DeleteButton.disabled='true';
    }
}

function openWin(url){
    var userForm = open(url,'userFormName','width=300,height=250,status=1,alwaysRaised=yes');
    userForm.moveTo((screen.width-300)/2,(screen.height-250)/2);
}
*/