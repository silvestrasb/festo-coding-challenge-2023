var MAXINT=1;
arr = [];
for(var i=1;i<54;i++,MAXINT+=MAXINT);MAXINT--;
var MAXINTsqrt=Math.floor(Math.sqrt(MAXINT));
function getElementById(x){return document.getElementById(x)};

function putmsg(txt,br){

arr.push(txt);

};


function isInt(i){
   if(typeof i!="number"&&typeof i!="string")return false;
   var s=i.toString().toLowerCase().replace(/\s/g,"");
   return !isNaN(i) && isFinite(i) && s!="" && s.indexOf(".")==-1  && s.indexOf("e")==-1  &&  i<=MAXINT
}  ;


function getInput(Fld,Fldnm,Cond){var valu;
	var x;
	if(getElementById(Fld))valu=getElementById(Fld).value
	else valu=Fld;
	Cond=(arguments.length<3?"":Cond);
	try{x=(Cond.indexOf("noeval")==-1||Cond.indexOf("eval")>-1
	      ?eval(valu)
	      :valu)}
	catch(e){x=Number.NaN};
    if(x==null)x="";
	if( (Cond.indexOf("O")>-1)|| Cond.indexOf("0")>-1)
	   {//putmsg("Cond0?:"+Cond.indexOf("0")+(x==""));
	     if( x==""  )
	     {//putmsg("<- ''");
	      return x}};

	if(x=="")
	  {if(Cond.indexOf("emptyval")>-1)
	     {var e=Cond.charAt(Cond.indexOf("emptyval=")+9);
	      //alert("EMPTY"+e+"]"+isInt(e));
	       if(isInt(e))return eval(e)
	       else return e
	     }
	   else{halt("Please enter a number for "+Fldnm)}
	  }
	if(isNaN(x)||!isFinite(x))
	  {halt("Please change your entry for "+Fldnm+": it is not a number ("+x+")")};
	//alert(Cond+" "+x+" type "+(typeof x)+ " isint:"+isInt(x));
	if(Cond.indexOf("z")>=0 && !isInt(x))
	  { if(x.toString().indexOf(".")!=-1 || x.toString().toLowerCase().indexOf("e")!=-1)
	        halt("Your value for "+Fldnm+" must be a whole number.")
	   else halt("Your value for "+Fldnm+" is too large - sorry.")
	   };
	if( (Cond.indexOf("Z")>=0||Cond.indexOf("N")>=0)
	   && (x==x+1))
	  halt("Your value for "+Fldnm+" is too large - sorry.");
	if(Cond.indexOf("Z")>=0 && Fld.replace(/[0-9]/g,"")==""
	    && Fld!=Math.round(Fld).toString())
	  halt(Fldnm+" is too large - sorry!");
	if(Cond.indexOf("+")>=0)
	  { if(!isInt(x))
	     halt(Fldnm+" must be a whole number>0")
	    else if(x<0) halt(Fldnm+" cannot be negative ("+x+")")
	  };
	if(Cond.indexOf("0")>=0 && x==0) HALT(Fldnm+" cannot be zero");
	return x };


function gcd(a,b){var g;
  if(arguments.length==1)
    {g=gcd1(a[0],a[1]);
      for(var i=2;g>1&&i<a.length;i++)g=gcd1(g,a[i]);
   }else{
      g=gcd1(arguments[0],arguments[1]);
      for(var i=2;g>1&&i<arguments.length;i++)g=gcd1(g,arguments[i]);
  };
  return g
};
function gcd1(a,b){
   a=Math.abs(a);
   b=Math.abs(b);
   while(a>0 && b>0){var aa=a;a=b%a;b=aa};
   return (a==0?b:a)
 };


//Egyption begin
function toEgypt(prefx,outp){
  if(arguments.length<2)outp=true;
  var t=getInput(prefx+"top","Numerator (top)","+"),
      b=getInput(prefx+"bot","Denominator (bottom)","+");
  var tt=t,bb=b;

  var efs=new Array(),g,d,pre=tt+"/"+bb+" = ";
  if(b<0){b=-b};
  if(t<0){t=-t};
  if(t==0){if(outp)putmsg(tt+"/"+bb+"=0");return 0};
  g=gcd(t,b);
  if(g>1){t=t/g;b=b/g;pre += t+"/"+b+(t>1?" = ":"")};
  //if(g>1)putmsg(tt+"/"+bb+" = "+t+"/"+b);
  if(b==1){if(outp)halt(t+"/"+b+"="+t+" is not a fraction")};
  if(t==1){if(outp){putmsg(t+"/"+b+" is a unit fraction already and is "+
     "1/"+(bb*2)+" + 1/"+(bb*3)+" + 1/"+(bb*6));return}};
  var w=Math.floor(t/b);if(w>0){t=t-w*b;pre += w+"+"+t+"/"+b+" = "+w+"+"};
   while(t>1&&isInt(t))
  {d=Math.ceil(b/t);
    if(!isInt(d)){b="too large";break;};
    efs[efs.length]=d;
    t=t*d-b;b=b*d;g=gcd(t,b);if(g>1){t=t/g;b=b/g};
   };
   if(!isInt(b)||!isInt(t))b="too large";
   efs[efs.length]=b;
  if(outp)putmsg(pre+(efs.length>1?"1/"+efs.join("+1/"):""));
  return efs.length;
 };

function tablLENS(){
   for(var tt=2;tt<=29;tt++)
     {document.ef1R.res.value += (tt<10?" ":"")+tt+"| ";
      for(var bb=3;bb<=30;bb++)
         {  if(tt>=bb){document.ef1R.res.value += ". "}
            else if(gcd(tt,bb)>1){document.ef1R.res.value +="- ";}
            else {var l=0;
				var t=tt,b=bb;
				while(t>1)
				{var d=Math.ceil(b/t);
				  if(d.toString().indexOf("e")!=-1){b="too large";break;};
				  l++
				 t=t*d-b;b=b*d;g=gcd(t,b);if(g>1){t=t/g;b=b/g};
				};l++
				document.ef1R.res.value += l+" "
				}
         };
      document.ef1R.res.value += "\r"
     }
};

var RES="ef2R";
var foundct=0, printing =true, find1=false, maxden,
     soln=new Array();
var ok;

function found(solen,maxtofind){foundct++;
  if(arguments.length<2)maxtofind=0;
  if(printing)putmsg( //foundct+") " +
      soln[0]+" = 1/"+soln.slice(1,solen+1).join(" + 1/"))
   if(maxtofind>0&&foundct>=maxtofind){ok=false}

};

function egylen(tt,bb,l,ind,maxden,maxct)
{var g; /// ind= last index filled in soln
  //alert(tt+"/"+bb+" len="+l+" maxden:"+maxden+" ctlim="+maxtofind);
 //document.ef2R.res.value += "egylen("+tt+","+bb+","+l+")\r";
  if(l==1){if(tt==1&&(ind==0||bb>soln[ind])&&(maxden>0?maxden>=bb:true))
      {soln[ind+1]=bb;found( ind+1,maxct)};return};
  //if(maxden>0&&maxden<bb)return;
  var t=tt,
      b=bb,
      lo=Math.ceil(b/t),
      hi=Math.floor(l*bb/tt);
  lo=(ind>1&&soln[ind]+1>lo?soln[ind]+1:lo);
  if(maxden>0&&maxden>hi)hi=maxden;
  if((maxden>0&&lo>maxden)||lo>hi)return ;

  // putmsg("egylen: "+tt+"/"+bb+" ["+soln.slice(0,ind)+"] l="+l+" "+lo+".."+hi+" maxden="+maxden);
 for(var q=lo;  ok&&q<=hi; q++)
	  {  //try 1/q... rest is t/b-1/q=(tq-b)/(bq);
		 t=tt*q-bb;
		 if(t>0)
		 { b=bb*q;g=gcd(t,b);if(g>1){t=t/g;b=b/g};
		   //document.ef2R.res.value += "try q="+q+" "+t+"/"+b+" g="+g+"\r";
		   if( ind==0||q>soln[ind])
		          {soln[ind+1]=q;
		           egylen(t,b,l-1,ind+1,maxden,maxct)}
		 }
	  }
};

function efFXDlen(L,t,b,maxden,maxct){

  var tt=t,bb=b;

  if(b<0){b=-b};
  if(t<0){t=-t};
  //if(b<=t){halt("The fraction is bigger than 1.  Try again.")};
  var g=gcd(t,b);
  if(g>1){tt=t;bb=b;t=t/g;b=b/g;};
  soln[0]=t+(b>1?"/"+b:"");
  foundct=0;ok=true;
     egylen(t,b,L,0,maxden,maxct);
  putmsg((g>1?tt+(bb>1?"/"+bb:"")+" = ":"")+t+(b>1?"/"+b:"")+": "
    +(foundct>0?(ok?"":"stopped at ")+foundct:"none")+" found of length "+L
    + (maxden>0?" with denominator up to "+maxden:""));
    for (let i = 0; i < arr.length; i++) {
      print(arr[i]); // here i represents index
    }
  return arr;
 };

function efdentable()
{  var d=parseInt(document.getElementById("fxdlenbot").value);
  printing=false;
for(var i=1;i<d;i++)f
   {document.getElementById("fxdlentop").value=i;
   efFXDlen()}
}

function eflen(prefx){
  var maxlen=7;
  maxden=0; maxtofind=100;
  var t=getInput(prefx+"top","Numerator (top)","+"),
      b=getInput(prefx+"bot","Denominator (bottom)","+");
  var tt=t,bb=b;
  var ll=0; //ll=l;
  if(b<=t){halt("The fraction is bigger than 1.  Try again.")};
  if(t==0)putmsg(tt+"/"+bb+"=0")
  else if(t==b)putmsg(tt+"/"+bb+"=1");
  g=gcd(t,b);
  if(g>1){t=t/g;b=b/g;putmsg(tt+"/"+bb+"="+t+"/"+b+(t>1?" = ":""))};
  if(b==1)putmsg(tt+"/"+bb+"="+tt);
  soln[0]=t+(b>1?"/"+b:"");
  foundct=0;
 // if(l==0)
  {while(++ll<=maxlen){ok=true;egylen(t,b,ll,0,0,0); if(foundct>0){break;}}}
  putmsg((ll>maxlen?tt+(bb>1?"/"+bb:"")+": none found up to length "
      +maxlen:"  "+foundct+" found of length "+ll));
};

function tablNB(which){var maxlen=7,max; printing=false;ok=true;
   switch(which){case 'nb':max=20;break;   case 'minlen':max=30;break };
   for(var t=2;t<=max-1;t++)
   {document.ef2R.res.value += "\r"+(t<10?" ":"")+t+"| ";
    for(var b=3;b<=max;b++)
    {if(t>=b)document.ef2R.res.value +=(which=='nb'?" ":"")+ " .";
    else if(gcd(t,b)>1)document.ef2R.res.value += (which=='nb'?" ":"")+" -"
     else {foundct=0;var ll=0;
           while(foundct==0 && ++ll<=maxlen){egylen(t,b,ll,0,0,0)};
           if(which=='nb')
             document.ef2R.res.value += (foundct<10?"  ":" ")+foundct+(ll>maxlen?"*":"")
           else if (which=='minlen')
             document.ef2R.res.value += " "+ll+(ll>maxlen?"*":"")
          }
   }};printing=true;
};

function tableUnder(Len,M){var c=new Array();printing=true;ok=true;
   for(var i=5;i<=M;i++)c[i]=efFXDlen(Len,4,i);
   document.ef3R.res.value+="\r"+c;
};
var outF;
function clearmsg(F){ if(arguments.length==0)F=outF;
   document.getElementById('msg'+F).innerHTML=''};
