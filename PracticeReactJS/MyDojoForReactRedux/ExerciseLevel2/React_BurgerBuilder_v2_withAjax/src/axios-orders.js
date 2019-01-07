import axios from 'axios';

const instance = axios.create({
    baseURL: 'https://react-my-burger-829ec.firebaseio.com/'
});

export default instance;