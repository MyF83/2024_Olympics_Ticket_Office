export interface CartsInterface {
    cart_id: number;
    date : string;
    totalAmount: number;
    user_id: {
        user_id: number;
        firstname: string;
        lastname: string;
        username: string;
        email: string;
        password: string;
        phoneNumber: string;
        address: string;
        creationDate: string;
        roles : {
            role_id: number;
            name: string;
            description: string | null;
        };
        userkeys : {
            userkey_id: number;
            date : string;
                keysgenerations : {
                key_id: number;
                keyGenerated: number;
                };
            };
        userselections : {
            usersel_id: number;
            nbPersons: number;
            amount: number;
                    offers : {
                        offer_id: number;
                        title: string;
                        description: string;
                        image: string;
                        rate: number;
                        nbSpectators: number;
                    };
                    events : {
                        event_id: number;
                        title: string;
                        date: string;
                        time : string;
                        image: string;
                        description: string;
                        sports: any; // If sports is null, you can use `any` or define its structure if needed
                        ceremonies: {
                            cerem_id: number;
                            name: string;
                            description: string;
                        sites: {
                            site_id: number;
                            name: string;
                            description: string;
                            address: string;
                            city: string;
                            postalCode: string;
                            countries: {
                            country_id: number;
                            name: string;
                            description: string | null;
                            };
                            nbseatsc1: number;
                            nbseatsc2: number;
                            nbseatsc3: number;
                        };
                        } | null; // Ceremonies can be null
                        challenger1: any; // If null, use `any` or define its structure
                        challenger2: any; // If null, use `any` or define its structure
                        pricec1: number;
                        nbseatssoldc1: number;
                        nbseatsavailc1: number;
                        pricec2: number;
                        nbseatssoldc2: number;
                        nbseatsavailc2: number;
                        pricec3: number;
                        nbseatssoldc3: number;
                        nbseatsavailc3: number;
                    };                        
            };
            policies : {
                policy_id: number;
                title: string;
                description: string;
                url: string;
                creationDate: string;
                version: string;
                isActive: boolean;
            };
        policySignDate: string;
        city: string;
        postalCode: string;
                countries: {
                    country_id: number;
                    name: string;
                };
    };
}

