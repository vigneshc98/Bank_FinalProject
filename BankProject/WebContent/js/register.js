const eye=document.querySelector('#togglePassword');
const pass=document.querySelector('#fcpass');

eye.addEventListener('click', function (){
    const type=pass.getAttribute('type') == 'password'? 'text':'password';
    pass.setAttribute('type',type);
    // this.classList.toggle('fa fa-eye');
    if(this.classList.contains('fa-eye-slash')){
        this.classList.remove('fa-eye-slash');
        this.classList.add('fa-eye');
    }else{
        this.classList.remove('fa-eye');
        this.classList.add('fa-eye-slash');
    }
});

// ---------------------------------------
const clearError=()=>{
    var hold=document.querySelectorAll('#formError');
    for(ele of hold){
        ele.innerHTML="";
    }
    let InputList=document.querySelectorAll('.inputs input');
    for(ele of InputList){
        ele.style.borderColor='black';
    }
}

function setError(id,error){
    element=document.getElementById(id);
    console.log(element);
    // element.getElementById('formError').innerHTML=error;  *dont work*
    element.querySelector('#formError').innerHTML=error;
}

function OnRegisterSubmit(){
    clearError();

    const formname=document.querySelector('#fname');
    const formemail=document.querySelector('#femail');
    const formpass=document.querySelector('#fpass');
    const formcpass=document.querySelector('#fcpass');
    const formphone=document.querySelector('#fphone');

    const fname=document.querySelector('#fname').value;
    console.log(fname);
    if(fname.length<4){
        setError("name"," *name length should be more than 3");
        formname.style.borderColor='red';
        return false;
    }
    formname.style.borderColor='black';

    const femail=document.querySelector('#femail').value;
    if(femail.length<13){
        setError("email"," *invalid Email");
        formemail.style.borderColor='red';
        return false;
    }
    formemail.style.borderColor='black';

    const pass=document.querySelector('#fpass').value;
    if(pass.length<7){
        setError("pass","*password too weak(min lenght-7)");
        formpass.style.borderColor='red';
        return false;
    }
    formpass.style.borderColor='black';
    let regx1=/[0-9]/;
    let regx2=/[A-Z]/;
    if(!pass.includes('@') || !regx1.test(pass) || !regx2.test(pass)){
        setError("pass","*should consist of atleast one @,0-9,A-Z character");
        formpass.style.borderColor='red';
        return false;
    }
    formpass.style.borderColor='black';

    const cpass=document.querySelector('#fcpass').value;
    if(pass!=cpass){
        setError("cpass","*Password not matching")
        formcpass.style.borderColor="red";
        return false;
    }
    formcpass.style.borderColor="black";

    const phone=document.querySelector('#fphone').value;
    console.log(phone);
    if(phone.length!=10){
        setError("phone","*invalid contact number")
        formphone.style.borderColor="red";
        return false;
    }
    formphone.style.borderColor="black";

    return true;

}


const checkConfirmPassword = (event)=>{
    let password = document.querySelector("#fpass").value;
    let current = event.target.value;
    // console.log(current);
    if(current!=password){
      setError("cpass","password not matching");
    }
    else{
        setError("cpass","");
    }
}
//------------------remove the max and min time of authentication page from local storage------------------
localStorage.removeItem("max");
localStorage.removeItem("min");
