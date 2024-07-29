use relm4::adw::prelude::*;
use relm4::prelude::*;

pub struct IdleClientModel;

#[derive(Debug)]
pub enum IdleClientOutput {
    GoToMatch,
}

#[relm4::component(pub)]
impl SimpleComponent for IdleClientModel {
    type Init = ();
    type Input = ();
    type Output = IdleClientOutput;

    view! {
        #[root]
        gtk::Box {
            set_orientation: gtk::Orientation::Vertical,
            set_hexpand: true,
            set_margin_all: 32,

            gtk::Label {
                set_label: "Connect to server",
                set_margin_all: 32,
                set_css_classes: &["title-1"],
            },
            adw::PreferencesGroup {
                adw::EntryRow {
                    set_valign: gtk::Align::Center,
                    set_title: "Address",
                    connect_changed[sender] => move |_entry|{
                        sender.output(Self::Output::GoToMatch).unwrap()
                    },
                }
            },
        }
    }

    fn init(
        _init: Self::Init,
        root: &Self::Root,
        sender: ComponentSender<Self>,
    ) -> ComponentParts<Self> {
        let model = IdleClientModel {};
        let widgets = view_output!();

        ComponentParts { model, widgets }
    }
}
