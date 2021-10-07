      

//------------------------------------------
const btn_save1=document.querySelector('.btn-save1');
const btn_save2=document.querySelector('.btn-save2');
const edit_button= document.querySelector('.btn-edit1');
const edit_profile=document.querySelector('#fileId');

edit_profile.classList.remove('form-child');
edit_profile.classList.toggle('non-active');

btn_save1.classList.remove('btn-save1');
btn_save1.classList.toggle('non-active');

btn_save2.classList.remove('btn-save2');
btn_save2.classList.toggle('non-active');

const sub_form_child=document.querySelectorAll('.sub-form-child');

edit_button.addEventListener('click', (e) =>{
    edit_profile.classList.remove('non-active');
    edit_profile.classList.toggle('form-child');

    btn_save1.classList.remove('non-active');
    btn_save1.classList.toggle('btn-save1');

    btn_save2.classList.remove('non-active');
    btn_save2.classList.toggle('btn-save2');

    edit_button.classList.remove('btn-edit1');
    edit_button.classList.toggle('non-active');
    

    for(i=0;i<=2;i++){
       sub_form_child[i].removeAttribute('readonly');
    }
    for( i=0;i<=2;i++){
        sub_form_child[i].classList.toggle('sub-form-child-temp');
    }
})

// -------------------Form Validation------------------------

const clearError=()=>{
    var hold=document.querySelectorAll('#formError');
    for(ele of hold){
        ele.innerHTML="";
    }
    let InputList=document.querySelectorAll('.form-main-child input');
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

function ProfileUpdateSubmit(){
    clearError();

    const formname=document.querySelector('#ip1');
    // const formemail=document.querySelector('#ip2');
    const formphone=document.querySelector('#ip3');
    const formplace=document.querySelector('#ip4');

    const fname=document.querySelector('#ip1').value;
    const phone=document.querySelector('#ip3').value;
    const fplace=document.querySelector('#ip4').value;

    console.log(fname+":"+phone+":"+fplace);

    if(fname!="" && phone!="" && fplace!=""){
        console.log(fname);
        if(fname.length<4){
            setError("Uname"," *name length should be more than 3");
            formname.style.borderColor='red';
            return false;
        }
        formname.style.borderColor='black';

        console.log(phone);
        if(phone.length!=10){
            setError("Uphone","*invalid contact number")
            formphone.style.borderColor="red";
            return false;
        }
        formphone.style.borderColor="black";
        
        console.log(fplace);
        if(fplace.length<4){
            setError("Uplace"," *place length should be more than 3");
            formplace.style.borderColor='red';
            return false;
        }
        formplace.style.borderColor='black';
    }
    else if(fname!="" && phone=="" && fplace==""){
        console.log(fname);
        if(fname.length<4){
            setError("Uname"," *name length should be more than 3");
            formname.style.borderColor='red';
            return false;
        }
        formname.style.borderColor='black';
    }
    else if(fname!="" && phone!="" && fplace==""){
        console.log(fname);
        if(fname.length<4){
            setError("Uname"," *name length should be more than 3");
            formname.style.borderColor='red';
            return false;
        }
        formname.style.borderColor='black';

        console.log(phone);
        if(phone.length!=10){
            setError("Uphone","*invalid contact number")
            formphone.style.borderColor="red";
            return false;
        }
        formphone.style.borderColor="black";

    }
    else if(fname!="" && phone=="" && fplace!=""){
        console.log(fname);
        if(fname.length<4){
            setError("Uname"," *name length should be more than 3");
            formname.style.borderColor='red';
            return false;
        }
        formname.style.borderColor='black';

        console.log(fplace);
        if(fplace.length<4){
            setError("Uplace"," *place length should be more than 3");
            formplace.style.borderColor='red';
            return false;
        }
        formplace.style.borderColor='black';
    }
    else if(fname=="" && phone!="" && fplace==""){
        console.log(phone);
        if(phone.length!=10){
            setError("Uphone","*invalid contact number")
            formphone.style.borderColor="red";
            return false;
        }
        formphone.style.borderColor="black";
    }
    else if(fname!="" && phone!="" && fplace==""){
        console.log(fname);
        if(fname.length<4){
            setError("Uname"," *name length should be more than 3");
            formname.style.borderColor='red';
            return false;
        }
        formname.style.borderColor='black';

        const phone=document.querySelector('#ip3').value;
        console.log(phone);
        if(phone.length!=10){
            setError("Uphone","*invalid contact number")
            formphone.style.borderColor="red";
            return false;
        }
        formphone.style.borderColor="black";
    }
    else if(fname=="" && phone!="" && fplace!=""){
        const phone=document.querySelector('#ip3').value;
        console.log(phone);
        if(phone.length!=10){
            setError("Uphone","*invalid contact number")
            formphone.style.borderColor="red";
            return false;
        }
        formphone.style.borderColor="black";
    
        const fplace=document.querySelector('#ip4').value;
        console.log(fplace);
        if(fplace.length<4){
            setError("Uplace"," *place length should be more than 3");
            formplace.style.borderColor='red';
            return false;
        }
        formplace.style.borderColor='black';
    }
    else if(fname=="" && phone=="" && fplace!=""){
        if(fplace.length<4){
            setError("Uplace"," *place length should be more than 3");
            formplace.style.borderColor='red';
            return false;
        }
        formplace.style.borderColor='black';
    }
    else if(fname!="" && phone=="" && fplace!=""){
        if(fname.length<4){
            setError("Uname"," *name length should be more than 3");
            formname.style.borderColor='red';
            return false;
        }
        formname.style.borderColor='black';

        console.log(fplace);
        if(fplace.length<4){
            setError("Uplace"," *place length should be more than 3");
            formplace.style.borderColor='red';
            return false;
        }
        formplace.style.borderColor='black';
    }
    else if(fname=="" && phone!="" && fplace!=""){
        console.log(phone);
        if(phone.length!=10){
            setError("Uphone","*invalid contact number")
            formphone.style.borderColor="red";
            return false;
        }
        formphone.style.borderColor="black";

        console.log(fplace);
        if(fplace.length<4){
            setError("Uplace"," *place length should be more than 3");
            formplace.style.borderColor='red';
            return false;
        }
        formplace.style.borderColor='black';

    }
    return true;

}
