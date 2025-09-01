const express = require ('express');
const bodyParser=require('body-parser')
const mongoose=require('mongoose')
const Student = require ('./model/data')

const app = express();
const port = 5000
app.set('view engine','ejs')
app.use(express.static("public"));
app.use(bodyParser.urlencoded({extended:true}))

mongoose.connect("mongodb://127.0.0.1:27017/studentDB")
app.get('/', async(req,res)=> {
    const students= await Student.find()

    res.render('index',{ students})
})

app.post('/save',async(req,res)=>{
    const {rollNo,name,degree}=req.body;
    const students=new Student ({rollNo,name,degree})
    await students.save()
  
res.redirect('/')

});




app.listen(port, () => {console.log(`server running on port No: ${port}`)})