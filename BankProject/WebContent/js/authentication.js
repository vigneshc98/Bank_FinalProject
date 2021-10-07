const span=document.querySelector('#span');

function  start() {
    let max=localStorage.getItem("max");
    let min=localStorage.getItem("min");

    if(max==null && min==null){
        localStorage.setItem("min",-1);
        localStorage.setItem("max",120);
        let min=localStorage.getItem("min");

        let hold= setInterval(()=>{ 
            let max=localStorage.getItem("max");
            console.log(min+":"+max);
            let res=min<max;
            console.log(res);
            if(min != max){
                span.innerHTML=max;
                max--;
               localStorage.setItem("max",max);
            }else{
                localStorage.setItem("max",120);
                clearInterval(hold);
            }
    },1000);
    } else if(max>0 && max <121){
        let hold= setInterval(()=>{ 
            let min=localStorage.getItem("min");
            let max=localStorage.getItem("max");
            console.log(min+":"+max);
            let res=min<max;
            console.log(res);
            if(min != max){
                span.innerHTML=max;
                max--;
               localStorage.setItem("max",max);
            }else{
                localStorage.setItem("max",120);
                clearInterval(hold);
            }
    },1000);
    }
    else{
        localStorage.setItem("min",-1);
        localStorage.setItem("max",120);
    }
}
 start();

// localStorage.clear();