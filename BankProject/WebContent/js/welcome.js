const icon_pressed1=document.querySelector('.icon-pressed1');
const icon_pressed2=document.querySelector('.icon-pressed2');
const icon_pressed3=document.querySelector('.icon-pressed3');
const mform1=document.querySelector('.mform1');
const mform2=document.querySelector('.mform2');
const mform3=document.querySelector('.mform3');

mform1.classList.remove('mform1');
mform1.classList.toggle('non-active');

mform2.classList.remove('mform2');
mform2.classList.toggle('non-active');

mform3.classList.remove('mform3');
mform3.classList.toggle('non-active');


icon_pressed1.addEventListener('click', () => {
    mform1.classList.remove('mform1');
    mform1.classList.toggle('non-active');
});
icon_pressed2.addEventListener('click', () => {
    mform2.classList.remove('mform2');
    mform2.classList.toggle('non-active');
});
icon_pressed3.addEventListener('click', () => {
    mform3.classList.remove('mform3');
    mform3.classList.toggle('non-active');
});


const Buttonmform1 =() => {
    if(mform1.classList.contains('non-active')){
        mform1.classList.remove('non-active');
        mform1.classList.toggle('mform1');
    }

}
const Buttonmform2 =() => {
    if(mform2.classList.contains('non-active')){
        mform2.classList.remove('non-active');
        mform2.classList.toggle('mform2');
    }

}
const Buttonmform3 =() => {
    if(mform3.classList.contains('non-active')){
        mform3.classList.remove('non-active');
        mform3.classList.toggle('mform3');
    }

}