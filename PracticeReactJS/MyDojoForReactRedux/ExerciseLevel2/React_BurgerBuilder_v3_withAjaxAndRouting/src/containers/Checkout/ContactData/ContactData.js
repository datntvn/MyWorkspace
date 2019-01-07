import React, {Component} from 'react';
import Button from '../../../components/UI/Button/Button';
import Spinner from '../../../components/UI/Spinner/Spinner';
import classes from './ContactData.css';
import axios from '../../../axios-orders';

class ContactData extends Component {
    state = {
        name: '',
        email: '',
        address: {
            street: '',
            postalCode: ''
        },
        loading: false
    }
    orderHandler = (event) => {
        event.preventDefault();
        console.log('g = ', this.props.ingredients);
        
        this.setState({
            loading: true
        });
        const order = {
            ingredients: this.props.ingredients,
            // price: this.state.totalPrice,
            price: this.props.price,
            customer: {
                name: "Henry Wong",
                address: {
                    street: "Teststreet 1",
                    zipCode: '123123',
                    country: 'Finland'
                },
                email: 'henw@abc.com'
            },
            deliveryMethod: 'fastest'
        }
        console.log("Post data: ", order);
        axios.post('/orders.json', order)
            .then(response => {
                console.log('POSTED: ',response)
                this.setState({loading:false});
                this.props.history.push('/');
            })
            .catch(err => {
                this.setState({loading:false});
                console.err("Error: ", err)
            });
            
    }
    render () {
        let form = (
            <form>
                    <input className={classes.Input} type="text" name="name" placeholder="Your Name" />
                    <input className={classes.Input} type="email" name="email" placeholder="Your Mail" />
                    <input className={classes.Input} type="text" name="street" placeholder="Street" />
                    <input className={classes.Input} type="text" name="postal" placeholder="Your Postal code" />
                    <Button btnType="Success" clicked={this.orderHandler}>
                        ORDER
                    </Button>
            </form>
        );
        if (this.state.loading) {
            form = <Spinner />;
        }
        return (
            <div className={classes.ContactData}>
                <h4>Enter your Contact Data</h4>
                {form}
            </div>
        )
    }
    
}

export default ContactData;