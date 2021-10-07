const eye=document.querySelector('#togglePassword');
const pass=document.querySelector('#passShow');

eye.addEventListener('click', function (){
    const type=pass.getAttribute('type') == 'password'? 'text':'password';
    pass.setAttribute('type',type);
    if(this.classList.contains('fa-eye-slash')){
        this.classList.remove('fa-eye-slash');
        this.classList.add('fa-eye');
    }else{
        this.classList.remove('fa-eye');
        this.classList.add('fa-eye-slash');
    }
});

//const clearit=document.querySelectorAll('.clearit');
//
//function OnLoginSubmit(){
//    for(ele of clearit){
//        ele.innerHTML='';
//    }
//}