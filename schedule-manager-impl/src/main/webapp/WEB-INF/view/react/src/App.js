import Header from './components/Header'
import Login from './components/Login'
import Registration from './components/Registration'

function App() {

  return (
    <>
      <div className='header'>
          <Header username='Geek'/>  
      </div>
      <div className='registration_container'>
        <Registration />
      </div>
      <div className='login_container'>
        <Login />
      </div>

      <div className='container'>
        
      </div>
    </>
  );
}

export default App;
